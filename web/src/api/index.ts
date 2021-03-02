import { Address } from "../types";

const HOST = "http://localhost:8080";

const GET = {
  method: "get",
  headers: { accept: "application/json" },
};

export const greeting = async ({
  country,
}: Address): Promise<[string, null] | [null, string]> => {
  try {
    const response = await fetch(`${HOST}/greeting?name=${country}`, GET);
    if (response.ok) {
      const result = JSON.parse(await response.text()) as { content: string };
      return [result.content, null];
    } else {
      return [null, `${response.status}\n${response.body}`];
    }
  } catch ({ message }: any) {
    return [null, message];
  }
};
