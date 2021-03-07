import { FC, useState } from "react";

import AddressField from "./AddressField";
import { FORMATS } from "./metadata";
import { CountryCode, Address, FieldDescriptor } from "./types";

const ALL_FORMAT = {
  name: "All",
  formats: [],
  enums: {},
};

const CountryForm: FC<{
  country: CountryCode | "All";
  value: Address;
  valueChange: (address: Address) => void;
}> = ({ country, value, valueChange }) => {
  const format = country === "All" ? { ...ALL_FORMAT } : FORMATS[country];

  const [address, addressChange] = useState(value);

  const fieldChanged = (field: FieldDescriptor, value: string) => {
    const newAddress = {
      ...address,
      [field.field]: value,
    };
    addressChange(newAddress);
    valueChange(newAddress);
  };

  return (
    <>
      <h3>{format.name}</h3>
      {format.formats[0].map((field) => (
        <AddressField
          field={field}
          enums={format.enums}
          value={address[field.field]}
          valueChange={(value) => fieldChanged(field, value)}
        />
      ))}
    </>
  );
};

export default CountryForm;
