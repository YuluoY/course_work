<template>
  <div class="tCourseList">
    <el-card ref="card">
      <el-divider content-position="left">课程预览</el-divider>
      <el-table
        :data="tableData" border style="width: 100%">
        <el-table-column prop="id" label="课程ID"></el-table-column>
        <el-table-column prop="name" label="课程名"></el-table-column>
        <el-table-column prop="type" label="类型"></el-table-column>
        <el-table-column prop="startTime" label="开课时间" :formatter="timeFormat"></el-table-column>
        <el-table-column prop="endTime" label="结束时间" :formatter="timeFormat"></el-table-column>
        <el-table-column prop="scount" label="人数"></el-table-column>
        <el-table-column label="操作" width="180">
          <template slot-scope="scope">
            <el-button type="primary" @click="preEdit(scope.row)">编辑</el-button>
            <el-button type="danger" @click="onDelete(scope.row)">删除</el-button>

            <el-dialog title="课程信息修改" :visible.sync="dialogFormVisible">
              <el-form>
                <el-form-item label="课程ID：" label-width="90px">
                  <el-input v-model="newForm.id" autocomplete="off" disabled></el-input>
                </el-form-item>
                <el-form-item label="课程名：" label-width="90px">
                  <el-input v-model="newForm.name" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="课程类型：" label-width="90px">
                  <el-input v-model="newForm.type" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="开课时间：" label-width="90px" style="text-align: left">
                  <el-date-picker v-model="newForm.startTime" autocomplete="off"></el-date-picker>
                </el-form-item>
                <el-form-item label="结束时间：" label-width="90px" style="text-align: left">
                  <el-date-picker v-model="newForm.endTime" autocomplete="off"></el-date-picker>
                </el-form-item>
              </el-form>
              <div slot="footer" class="dialog-footer">
                <el-button @click="onEdit(false)">取 消</el-button>
                <el-button type="primary" @click="onEdit(true)">确 定</el-button>
              </div>
            </el-dialog>
          </template>
        </el-table-column>

      </el-table>
    </el-card>
  </div>
</template>

<script>

  import { mapState } from "vuex";
  import { compareWithTwoObj, formatDate } from "@/util";

  export default {
    name: "tCourseList",
    data() {
      return {
        tableData: [],
        newForm: {},
        rawForm: {},
        dialogFormVisible: false
      };
    },
    methods: {
      async getCourseList() {
        await this.$request(this.$apis.Teacher.findCourseList, { id: this.userForm.id }, "tableData");
      },
      timeFormat: (data, event) => formatDate(data[event.property], "YYYY年MM月DD日"),
      preEdit(row) {
        this.newForm = { ...row };
        this.rawForm = row;
        this.dialogFormVisible = true;
      },
      onEdit(bool) {
        if (bool && !compareWithTwoObj(this.newForm, this.rawForm)) {
          this.newForm.identity = 4;
          this.newForm.startTime = formatDate(this.newForm.startTime, "YYYY-MM-DD");
          this.newForm.endTime = formatDate(this.newForm.endTime, "YYYY-MM-DD");
          this.$request(this.$apis.Course.updateCourse, this.newForm).then(res => {
            if (res) {
              this.$notify.success(`课程"${this.rawForm.name}"修改成功！`);
              this.getCourseList();
            }
          });
        }
        this.dialogFormVisible = false;
      },
      onDelete(row) {
        this.$request(this.$apis.Course.deleteById(row.id)).then(res => {
          if(res){
            this.$notify.success(`课程"${row.name}"已被删除！`);
            this.getCourseList();
          }
        });
      }
    },
    created() {
      this.getCourseList();
    },
    computed: {
      ...mapState(["userForm"])
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