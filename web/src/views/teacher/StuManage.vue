<template>
  <div class="stuManage">
    <el-card>
      <el-divider content-position="left">查看学生</el-divider>

      <div style="text-align: left">
        <span>课程选择：</span>
        <el-select v-model="currentSelectedCourseId" placeholder="请选择课程" @change="onChangeCourse">
          <el-option
            v-for="(item,i) in allCourse"
            :key="i"
            :label="item.name"
            :value="item.id">
          </el-option>
        </el-select>
      </div>
      <el-table
        empty-text="暂无学生选择该课程"
        :data="tableData"
        style="width: 100%">

        <el-table-column prop="id" label="ID"></el-table-column>
        <el-table-column prop="name" label="姓名"></el-table-column>
        <el-table-column prop="major" label="专业"></el-table-column>
        <el-table-column label="课程分数">
          <template slot-scope="scope">
            <el-link type="primary"
                     @click="onReview(scope.row)">
              {{ scope.row.score || "待批阅" }}
            </el-link>
            <el-dialog title="课程评分" :visible.sync="dialogFormVisible">
              <el-form :model="form">
                <el-form-item label="课程分数：" label-width="120px" required>
                  <el-input v-model.number="form.score" autocomplete="off"></el-input>
                </el-form-item>
              </el-form>
              <!--              <el-button @click="dialogFormVisible = false">取消</el-button>-->
              <el-button type="primary" @click="onConfirmReview">批阅</el-button>
            </el-dialog>
          </template>
        </el-table-column>

        <el-table-column prop="grade" label="班级"></el-table-column>
        <el-table-column prop="age" label="年龄"></el-table-column>
        <el-table-column prop="gender" label="性别"></el-table-column>
        <el-table-column prop="address" label="地址" width="150px"></el-table-column>
      </el-table>
    </el-card>
  </div>
</template>

<script>
  import { mapState } from "vuex";

  export default {
    name: "StuManage",
    data() {
      return {
        tableData: [],
        allCourse: [],
        currentSelectedCourseId: null,
        dialogFormVisible: false,
        form: {}
      };
    },
    methods: {
      onReview(row) {
        this.form = { ...row };
        this.dialogFormVisible = true;
      },
      async onConfirmReview() {
        this.dialogFormVisible = false;
        if (!this.form.score) return;
        const obj = {
          cId: this.currentSelectedCourseId,
          sId: this.form.id,
          score: this.form.score
        };
        this.$request(this.$apis.Teacher.reviewCourse, obj).then(res => {
          if (res) {
            this.$notify({
              type: "info",
              message: `学生：${this.form.name}<br>
                        课程：${this.form.cname}<br>
                        分数：${this.form.score}`,
              dangerouslyUseHTMLString: true
            });
            this.findSelectedCourseStudents(this.currentSelectedCourseId);
          }
        });
      },
      async findAllCourse() {
        await this.$request(this.$apis.Teacher.findCourseList, { id: this.userForm.id }, "allCourse");
        setTimeout(this.$set(this, "currentSelectedCourseId", this.allCourse[0].id));
      },
      async findSelectedCourseStudents(cId = null) {
        const obj = {
          tId: this.userForm.id,
          cId: cId || this.allCourse[0].id
        };
        await this.$request(this.$apis.Teacher.findSelectedCourseStudents, obj, "tableData");
        // if (!cId) setTimeout(this.$set(this.tableData, "currentSelectedCourseId", this.allCourse[0].id));
      },
      onChangeCourse() {
        this.findSelectedCourseStudents(this.currentSelectedCourseId);
      }
    },
    computed: {
      ...mapState(["userForm"])
    },
    created() {
      this.findAllCourse().then(_ => this.findSelectedCourseStudents());

    }
  };
</script>

<style scoped>
    /deep/ .el-table th > .cell {
        text-align: center;
    }

    /deep/ .el-table .cell {
        text-align: center;
    }
</style>