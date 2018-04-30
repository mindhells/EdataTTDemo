export type Role = "admin"|"standard";

export class User {
    id: number;
    name: string;
    login: string;
    password?: string;
    roles: Role[]
}
