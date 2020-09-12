export default class UserToken {
  public token: string;

  constructor(token: string) {
    this.token = token;
  }

  getToken(): string {
    return this.token;
  }

  isExpired(): boolean {
    return false;
  }
}