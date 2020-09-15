export default interface ReducerMessage {
  type: "info" | "warning" | "error" | "success";
  status: number;
  message: string;
  timestamp: number;
}