import { FC } from "react";

import AddressField from "./AddressField";
import { FORMATS } from "./metadata";
import { CountryCode } from "./types";

const ALL_FORMAT = {
  name: "All",
  formats: [],
  enums: {},
};

const CountryForm: FC<{ country: CountryCode | "All" }> = ({ country }) => {
  const format = country === "All" ? { ...ALL_FORMAT } : FORMATS[country];
  return (
    <>
      <h3>{format.name}</h3>
      {format.formats[0].map((field) => (
        <AddressField field={field} enums={format.enums} />
      ))}
    </>
  );
};

export default CountryForm;
