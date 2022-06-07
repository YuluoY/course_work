<template>
  <div class="courseScore">
    <el-card ref="card">
      <el-table :data="tableData" style="width: 100%;" border>
        <el-table-column prop="id" label="课程ID"></el-table-column>
        <el-table-column prop="name" label="课程名称"></el-table-column>
        <el-table-column prop="type" label="课程类型"></el-table-column>
        <el-table-column prop="tname" label="授课教师"></el-table-column>
        <el-table-column prop="school" label="所属学院"></el-table-column>
        <el-table-column label="课程分数">
          <template slot-scope="scope">
            <div>{{ scope.row.score || "待批改" }}</div>
          </template>
        </el-table-column>
        <el-table-column prop="startTime" label="开课时间" :formatter="timeFormat"></el-table-column>
        <el-table-column prop="endTime" label="结课时间" :formatter="timeFormat"></el-table-column>
      </el-table>
    </el-card>
  </div>
</template>

<script>
  import { formatDate, loading } from "@/util";
  import { mapState } from "vuex";

  export default {
    name: "CourseScore",
    data() {
      return {
        tableData: []
      };
    },
    methods: {
      async findStuCurriculumScore() {
        const obj = { id: this.userForm.id };
        await this.$request.call(this, this.$apis.Student.findStuCourse, obj, "tableData");
      },
      timeFormat(data, event) {
        return formatDate(data[event.property], "YYYY年MM月DD日");
      }
    },
    computed: {
      ...mapState(["userForm"])
    },
    mounted() {
      const loadingInstance = loading(this.$refs.card.$el, "数据加载中...");
      this.findStuCurriculumScore().then(_ => loadingInstance.close());
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