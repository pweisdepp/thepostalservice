import { useState } from "react";

import { greeting } from "./api";
import AddressForm from "./AddressForm";
import Footer from "./Footer";
import { Address } from "./types";

function App() {
  const [address, setAddress] = useState<Address>({ country: "" });
  const [response, setResponse] = useState<string | null>(null);
  const [error, setError] = useState<string | null>(null);

  const formChange = (address: Address) => {
    setAddress(address);
  };

  const lookup = async () => {
    const [response, error] = await greeting(address);
    setResponse(response);
    setError(error);
  };

  return (
    <div className="container">
      <h1>The Postal Service</h1>
      <AddressForm onSubmit={lookup} onChange={formChange} />
      <button type="button" className="btn btn-primary" onClick={lookup}>
        Lookup
      </button>
      {response || error ? (
        <div className={"mt-4 alert alert-" + (error ? "danger" : "success")}>
          {response || error}
        </div>
      ) : null}
      <Footer />
    </div>
  );
}

export default App;
