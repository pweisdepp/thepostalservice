import {useState} from 'react';

import {Address} from './types';

function Form({onChange}: {onChange: (e: Address) => void}) {
  const [address, setAddress] = useState<Address>({country: ''});

  const countryChange = (country: string) => {
    setAddress({...address, country});
    onChange(address);
  }

  return (
    <form>
      <div className="mb-3">
        <label htmlFor="country" className="form-label">Country</label>
        <input type="text"
                name="country"
                className="form-control"
                value={address.country}
                onChange={e => countryChange(e.target.value)} />
      </div>
    </form>
  );
}

export default Form;