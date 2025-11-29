import http from "./http";

export function login(userAct, userPwd) {
  return http.post("/api/auth/login", { userAct, userPwd });
}

export function register(payload) {
  return http.post("/api/auth/register", payload);
}
