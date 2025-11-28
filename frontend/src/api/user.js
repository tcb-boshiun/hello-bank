import http from "./http";

export function getUserInfo(userAct) {
  return http.get(`/api/users/${userAct}`);
}

export function updateUserInfo(userAct, data) {
  return http.put(`/api/users/${userAct}`, data);
}
