import { useState } from "react";

import { countrySearch } from "./api";
import AddressForm from "./AddressForm";
import Footer from "./Footer";
import { Address } from "./types";
import AddressResponses from "./AddressResponses";

function newAddress(): Address {
  return { country: "All" };
}

function App() {
  const [address, setAddress] = useState<Address>(newAddress());
  const [response, setResponse] = useState<Address[] | null>(null);
  const [error, setError] = useState<string | null>(null);

  const formChange = (address: Address) => {
    console.log(`Form Change, address is ${JSON.stringify(address)}`);
    setAddress(address);
  };

  const lookup = async () => {
    const [response, error] = await countrySearch(address);
    setResponse(response);
    setError(error);
  };

  return (
    <div className="container">
      <h1>The Postal Service</h1>
      <AddressForm address={address} onSubmit={lookup} onChange={formChange} />
      <button type="button" className="btn btn-primary" onClick={lookup}>
        Lookup
      </button>
      <div className="mt-4 container">
        {error ? (
          <div className="alert alert-danger">{error}</div>
        ) : response ? (
          <AddressResponses responses={response} />
        ) : null}
      </div>
      <Footer />
    </div>
  );
}

export default App;
