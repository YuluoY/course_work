<template>
  <div class="pubCourse">
    <el-card>
      <el-divider content-position="left">发布课程</el-divider>
      <el-form>
        <el-form-item label="课程ID：" label-width="100px">
          <el-input v-model.number="formData.id"></el-input>
        </el-form-item>
        <el-form-item label="课程名称：" label-width="100px">
          <el-input v-model.trim="formData.name"></el-input>
        </el-form-item>
        <el-form-item label="课程类型：" label-width="100px" style="text-align: left">
          <el-radio v-model.trim="formData.type" label="必修"></el-radio>
          <el-radio v-model.trim="formData.type" label="选修"></el-radio>
        </el-form-item>
        <el-form-item label="开课时间：" label-width="100px" style="text-align: left">
          <el-date-picker v-model.trim.number="formData.startTime"></el-date-picker>
        </el-form-item>
        <el-form-item label="结束时间：" label-width="100px" style="text-align: left">
          <el-date-picker v-model="formData.endTime"></el-date-picker>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onRelease">发布课程</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script>
  import { mapState } from "vuex";
  import { formatDate } from "@/util";

  export default {
    name: "PubCourse",
    data() {
      return {
        formData: {
          type: "必修",
          startTime: Date.now(),
          endTime: (Date.now() + 86400000),
          identity: 4
        }
      };
    },
    methods: {
      async onRelease() {
        this.formData.teacherId = this.userForm.id;
        this.formData.startTime = formatDate(this.formData.startTime, "YYYY-MM-DD");
        this.formData.endTime = formatDate(this.formData.endTime, "YYYY-MM-DD");
        const res = await this.$request(this.$apis.Course.addCourse, this.formData);
        if (res) {
          this.$notify.success(`课程"${this.formData.name}"添加成功！`);
          await this.$router.push("/tCourseList");
          setTimeout(await this.autoSelectedMustStudy, 1000);
        }
      },
      async autoSelectedMustStudy() {
        if (this.formData.type === "必修") {
          const { data } = await this.$request(this.$apis.Student.findAll);
          const obj = {
            cId: this.formData.id,
            sIds: []
          };
          data.forEach(item => obj.sIds.push(item.id));
          this.$request(this.$apis.Teacher.batchInsertRequiredCourse, obj).then(res => {
            console.log(res);
          });
        }
        this.formData = {};
      }
    },
    computed: {
      ...mapState(["userForm"])
    }
  };
</script>

<style scoped>

</style>