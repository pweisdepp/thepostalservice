import {useState} from 'react';

import Input from './form/Input';
import Form from './form/Form';
import {Address} from './types';

function AddressForm(
    {onChange, onSubmit}:
    {onChange: (e: Address) => void, onSubmit: () => void}
) {
  const [address, setAddress] = useState<Address>({country: ''});

  const countryChange = (country: string) => {
    setAddress({...address, country});
    onChange(address);
  }

  return (
    <Form onSubmit={onSubmit}>
      <Input name="Country" value="address.country" valueChange={countryChange} />
    </Form>
  );
}

export default AddressForm;