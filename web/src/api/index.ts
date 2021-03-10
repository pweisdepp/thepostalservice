import { Address } from "../types";

type ApiResult<T> = [T, null] | [null, string];

const HOST = "http://localhost:8080";

const GET = {
  method: "get",
  headers: { accept: "application/json" },
};

const POST = {
  method: "post",
  headers: { Accept: "application/json", "Content-Type": "application/json" },
};

const doApi = <T>(fn: (address: Address) => Promise<Response>) => {
  return async (address: Address): Promise<ApiResult<T>> => {
    try {
      const response = await fn(address);
      if (response.ok) {
        const result = (await response.json()) as T;
        return [result, null];
      } else {
        return [null, `${response.status}\n${await response.text()}`];
      }
    } catch ({ message }: any) {
      return [null, message];
    }
  };
};

export const greeting = doApi<string>(({ country }) =>
  fetch(`${HOST}/search/greeting?name=${country}`, GET)
);

export const countrySearch = doApi<Address[]>((address) => {
  const { country } = address;
  const copy: Partial<Address> = { ...address };
  delete copy.country;
  delete copy.street_type;
  for (const [k, v] of Object.entries(copy)) {
    if (!v) {
      delete copy[k];
    }
  }
  return fetch(`${HOST}/search/${country}`, {
    ...POST,
    body: JSON.stringify(copy),
  });
});
