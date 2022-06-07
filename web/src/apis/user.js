export const login = { method: "post", url: "/user/login" };
export const findAll = { method: "get", url: "/user/findAll" };

export const deleteUser = function(id) {
  return { method: "delete", url: `/rest/deleteById/${id}/0` };
};
export const addUser = { method: "post", url: "/rest/add" };
export const updateUser = { method: "patch", url: "/rest/update" };

export const findSchools = { method: "get", url: "/user/findAllSchool" };