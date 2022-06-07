<template>
  <div>
    <el-card>
      <el-form>
        <el-divider content-position="left">{{ title }}</el-divider>
        <el-form-item label="课程ID：" label-width="100px">
          <el-input v-model.trim.number="formData.id"></el-input>
        </el-form-item>
        <el-form-item label="课程名称：" label-width="100px">
          <el-input v-model.trim="formData.name"></el-input>
        </el-form-item>
        <el-form-item label="课程类型：" label-width="100px" style="text-align: left">
          <el-radio label="必修" v-model="formData.type"></el-radio>
          <el-radio label="选修" v-model="formData.type"></el-radio>
        </el-form-item>
        <el-form-item label="开课学院：" label-width="100px" style="text-align: left">
          <el-select v-model="formData.schoolId" :disabled="JSON.stringify(currEdit) !== '{}' ? true:false"
                     placeholder="请选择学院">
            <el-option
              v-for="item in formData.schools"
              :key="item.id"
              :label="item.school"
              :value="item.id">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="授课教师：" label-width="100px" style="text-align: left">
          <el-select v-model="formData.teacherId" placeholder="请选择教师" @change="onChangeTeacher">
            <el-option
              v-for="item in formData.teachers"
              :key="item.id"
              :label="item.name"
              :value="item.id">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="开课时间：" label-width="100px" style="text-align: left">
          <el-date-picker v-model="formData.startTime"></el-date-picker>
        </el-form-item>
        <el-form-item label="结束时间：" label-width="100px" style="text-align: left">
          <el-date-picker v-model="formData.endTime"></el-date-picker>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click.prevent.native="onSubmit">提交</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script>
  import { mapMutations, mapState } from "vuex";
  import { deepClone, formatDate, getFiledValidate, isEmptyObj, setCourseEndTime } from "@/util";

  export default {
    data() {
      return {
        formData: {
          teachers: [],
          type: "必修",
          startTime: Date.now(),
          endTime: (Date.now() + 86400000),
          identity: 4
        },
        title: "添加课程",
        teacherToSchoolMapping: new Map()
      };
    },
    methods: {
      onChangeTeacher(teacherId) {
        this.formData.schoolId = this.teacherToSchoolMapping.get(teacherId);
      },
      async findTeachers() {
        const { data } = await this.$request.call(this, this.$apis.Teacher.findTeachers);
        this.$set(this.formData, "teachers", data);
      },
      async findSchools() {
        const { data } = await this.$request.call(this, this.$apis.User.findSchools);
        this.$set(this.formData, "schools", data);
      },
      async findTeacherToSchoolMapping() {
        const { data } = await this.$request(this.$apis.Teacher.findTeacherToSchool);
        data.forEach(item => this.teacherToSchoolMapping.set(item.tId, item.sId));
      },
      async onSubmit() {
        const isCurrEdit = isEmptyObj(this.currEdit);
        let api = isCurrEdit ? this.$apis.Course.addCourse : this.$apis.Course.updateCourse;
        this.formData.startTime = formatDate(this.formData.startTime, "YYYY-MM-DD");
        this.formData.endTime = formatDate(this.formData.endTime, "YYYY-MM-DD");
        const res = await this.$request(api, this.formData);
        if (res) {
          this.$message.success(`课程"${this.formData.name}"${isCurrEdit ? "添加" : "编辑"}成功！`);
          this.formData = {};
          await this.$router.push("/aCourseList");
        }
      },
      ...mapMutations(["setCurrEdit"])
    },
    computed: {
      ...mapState(["currEdit", "userForm", "curriculum"])
    },
    created() {
      this.findTeachers();
      this.findSchools();
      this.findTeacherToSchoolMapping();
    },
    mounted() {
      if (!isEmptyObj(this.currEdit)) {
        this.formData = { ...this.currEdit };
        this.formData.identity = 4;
        this.title = "编辑课程";
      } else {
        this.title = "添加课程";
      }
    },
    beforeDestroy() {
      this.setCurrEdit({});
    }
  };
</script>

<style scoped>

</style>