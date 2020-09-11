export default interface RequestError {
  status: number;
  error: string;
  message: string;
  timestamp: number;
  path: string;
}