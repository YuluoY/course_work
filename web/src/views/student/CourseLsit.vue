<template>
  <div id="courseList">
    <el-card ref="card">
      <el-table
        :data="tableData" border
        style="width: 100%">
        <el-table-column prop="id" label="课程ID"></el-table-column>
        <el-table-column prop="name" label="课程名"></el-table-column>
        <el-table-column prop="type" label="类型"></el-table-column>
        <el-table-column prop="school" label="学院"></el-table-column>
        <el-table-column prop="tname" label="授课老师"></el-table-column>
        <el-table-column prop="startTime" label="开课时间" :formatter="timeFormat"></el-table-column>
        <el-table-column prop="endTime" label="结束时间" :formatter="timeFormat"></el-table-column>
        <el-table-column label="课程状态" width="180">
          <template slot-scope="scope">
            <div>{{ courseState(scope) }}</div>
          </template>
        </el-table-column>
      </el-table>
    </el-card>
  </div>
</template>

<script>
  import { mapState } from "vuex";
  import { formatDate, loading } from "@/util";
  import dayjs from "dayjs";

  export default {
    name: "CourseList",
    data() {
      return {
        tableData: []
      };
    },
    methods: {
      async findStuCourse() {
        await this.$request.call(this, this.$apis.Student.findStuCourse, { id: this.userForm.id }, "tableData");
      },
      timeFormat(data, event) {
        return formatDate(data[event.property], "YYYY年MM月DD日");
      },
      courseState(scope) {
        const isStart = scope.row.startTime - Date.now();
        if (isStart > 0) {
          return `${Math.floor(isStart / 1000 / 60 / 60 / 24)}天后开课`;
        }
        const duration = Date.now() - scope.row.endTime;
        return duration > 0 ? "已过期" : `${dayjs(duration).date()}天后结束`;
      }
    },
    computed: {
      ...mapState(["userForm"])
    },
    mounted() {
      const loadingInstance = loading(this.$refs.card.$el, "数据加载中...");
      this.findStuCourse().then(_ => loadingInstance.close());
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