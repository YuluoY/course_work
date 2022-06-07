<template>
  <div class="courseSelect">
    <el-card ref="card">
      <el-table :data="tableData" border style="width: 100%">
        <el-table-column prop="id" label="课程ID"></el-table-column>
        <el-table-column prop="name" label="课程名"></el-table-column>
        <el-table-column prop="type" label="类型"></el-table-column>
        <el-table-column prop="school" label="学院"></el-table-column>
        <el-table-column prop="tname" label="授课老师"></el-table-column>
        <el-table-column prop="startTime" label="开课时间" :formatter="timeFormat"></el-table-column>
        <el-table-column prop="endTime" label="结束时间" :formatter="timeFormat"></el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button type="primary" @click="onApplyOrCancel(scope.row, 1)" v-if="!scope.row.isSelected">选修
            </el-button>
            <el-button type="danger" @click="onApplyOrCancel(scope.row, -1)" v-if="scope.row.isSelected">取消
            </el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>
  </div>
</template>

<script>
  import { confirmTip, formatDate, loading } from "@/util";

  export default {
    name: "CourseSelect",
    data() {
      return {
        tableData: [],
        studentToCourseMapping: new Map(),
        temp: 0
      };
    },
    methods: {
      async onApplyOrCancel(row, code) {
        let res = null;
        if (code > 0) {
          res = await confirmTip.call(this, `确定选修课程"${row.name}"吗`, "提示", "info");
        } else {
          res = await confirmTip.call(this, `确定取消选修课程"${row.name}"吗`, "警告", "error");
        }
        let api = code > 0 ? this.$apis.Student.addCourse : this.$apis.Student.cancelSelectedCourse;

        if (res) {
          const obj = { sId: this.$store.state.userForm.id, cId: row.id };
          const loadingInstance = loading(this.$refs.card.$el, "数据更新中...");
          this.$request(api, obj).then(_ => {
            this.$notify.success(code > 0 ? `课程"${row.name}"选修成功，注意开课时间哦！` : `课程"${row.name}"已被取消选修！`);
            this.findStuToCourseMapping();
            this.findCurriculum();
            setTimeout(_ => {
              loadingInstance.close();
              this.$set(this, "temp", Math.random());
              this.$forceUpdate();
            }, 500);
          });
        }
      },
      async onCancel(row) {
        const res = await confirmTip.call(this, `确定取消选修课程"${row.name}"吗`, "警告", "error");
        if (res) {
          const obj = { sId: this.$store.state.userForm.id, cId: row.id };
          this.$request(this.$apis.Student.cancelSelectedCourse, obj).then(_ => {
            this.$notify.success(`课程"${row.name}"已被取消选修！`);
            this.findStuToCourseMapping();
            this.findCurriculum();
          });
        }
      },
      findCurriculum() {
        setTimeout(_ => {
          this.$request(this.$apis.Student.findCurriculum).then(res => {
            res.data.forEach((item, index, arr) => arr[index]["isSelected"] = this.studentToCourseMapping.has(item.id));
            this.$set(this, "tableData", res.data);
          });
        }, 500);
      },
      async findStuToCourseMapping() {
        const map = new Map();
        const obj = { id: this.$store.state.userForm.id };
        const { data } = await this.$request(this.$apis.Student.findStuToCourseMapping, obj);
        Promise.resolve().then(_ => data.forEach(item => map.set(item.courseId, item.id)));
        setTimeout(_ => this.$set(this, "studentToCourseMapping", map), 0);
      },
      timeFormat(data, event) {
        return formatDate(data[event.property], "YYYY年MM月DD日");
      }
    },
    created() {
      this.findStuToCourseMapping();
      this.findCurriculum();
    },
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