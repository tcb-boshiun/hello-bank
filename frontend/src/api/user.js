import http from "./http";

export function getUserInfo(userAct) {
  return http.get(`/api/user/${encodeURIComponent(userAct)}`);
}

export function updateUserInfo(data) {
  return http.put(`/api/user/${encodeURIComponent(data.userAct)}`, data);
}
