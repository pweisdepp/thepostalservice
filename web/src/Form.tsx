import {useState} from 'react';

import {Address} from './types';

function Form(
    {onChange, onSubmit}:
    {onChange: (e: Address) => void, onSubmit: () => void}
) {
  const [address, setAddress] = useState<Address>({country: ''});

  const countryChange = (country: string) => {
    setAddress({...address, country});
    onChange(address);
  }

  return (
    <form onSubmit={(e) => (e.preventDefault(), onSubmit())}>
      <div className="mb-3">
        <label htmlFor="country" className="form-label">Country</label>
        <input type="text"
                name="country"
                className="form-control"
                value={address.country}
                onChange={e => countryChange(e.target.value)}
                onBlur={e => countryChange(e.target.value)} />
      </div>
    </form>
  );
}

export default Form;