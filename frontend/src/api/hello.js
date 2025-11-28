import http from "./http";

export function getHello() {
  return http.get("/api/hello");
}

export function echo(message) {
  return http.post("/api/echo", message, {
    headers: { "Content-Type": "text/plain" },
  });
}

export function echoV2(message) {
  return http.post("/api/echo/v2", message, {
    headers: { "Content-Type": "text/plain" },
  });
}
