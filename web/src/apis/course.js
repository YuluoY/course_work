export const findAllCourse = { method: "get", url: "/user/findAllCourse" };

export const deleteById = function(id) {
  return { method: "delete", url: `/rest/deleteById/${id}/4` };
};
export const addCourse = { method: "post", url: "/rest/add" };
export const updateCourse = { method: "patch", url: "/rest/update" };