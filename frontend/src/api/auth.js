import http from "./http";

export function login(userAct, userPwd) {
  return http.post("/api/auth/login", { userAct, userPwd });
}

export function register(data) {
  return http.post("/api/auth/register", data);
}
