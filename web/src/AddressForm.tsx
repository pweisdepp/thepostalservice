import { FC, useState } from "react";

import Select from "./form/Select";
import Form from "./form/Form";
import CountryForm from "./CountryForm";
import { Address, CountryCode } from "./types";
import { countries } from "./metadata";

const AddressForm: FC<{
  address: Address;
  onChange: (address: Address) => void;
  onSubmit: () => void;
}> = ({ address, onChange, onSubmit }) => {
  const [_address, setAddress] = useState<Address>(address);

  const countryChange = (country: string) => {
    console.log(`Country changed to ${country}`);
    const addr =
      country == "All"
        ? address
        : { ...address, country: country as CountryCode };
    setAddress(addr);
    onChange(addr);
  };

  return (
    <Form onSubmit={onSubmit}>
      <Select
        name="Country"
        value={_address.country}
        values={countries()}
        valueChange={countryChange}
        all={true}
      />
      {_address.country == "All" ? (
        []
      ) : (
        <CountryForm country={_address.country} />
      )}
    </Form>
  );
};

export default AddressForm;
