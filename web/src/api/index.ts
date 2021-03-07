import { Address } from "../types";

type ApiResult<T> = [T, null] | [null, string];

const HOST = "http://localhost:8080";

const GET = {
  method: "get",
  headers: { accept: "application/json" },
};

const POST = {
  method: "get",
  headers: { Accept: "application/json", "Content-Type": "application/json" },
};

const doApi = <T>(fn: (address: Address) => Promise<Response>) => {
  return async (address: Address): Promise<ApiResult<T>> => {
    try {
      const response = await fn(address);
      if (response.ok) {
        const result = JSON.parse(await response.text()) as { content: T };
        return [result.content, null];
      } else {
        return [null, `${response.status}\n${response.body}`];
      }
    } catch ({ message }: any) {
      return [null, message];
    }
  };
};

export const greeting = doApi<string>(({ country }) =>
  fetch(`${HOST}/search/greeting?name=${country}`, GET)
);

export const countrySearch = doApi<Address>((address) => {
  const { country } = address;
  return fetch(`${HOST}/search/${country}`, {
    ...POST,
    body: JSON.stringify(address),
  });
});
