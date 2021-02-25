import {useEffect, useState} from 'react';

import Form from './Form';
import Footer from './Footer';
import {Address} from './types';

function App() {
  const [address, setAddress] = useState<Address>({country: ''});
  const [response, setResponse] = useState<string>('');

  const formChange = (address: Address) => {
    setAddress(address);
  };

  const lookup = async () => {
    const response = await fetch(`http://localhost:8080/greeting?name=${address.country}`, {
      method: "get",
      headers: {"accept": "application/json"}
    });
    if (response.ok) {
      const result = JSON.parse(await response.text()) as {content: string};
      setResponse(result.content);
    }
  };

  return (
    <div className="container">
      <h1>The Postal Service</h1>
      <Form onSubmit={lookup} onChange={formChange} />
      <button type="button" className="btn btn-primary" onClick={lookup}>Lookup</button>
      {(response !== '') ? <p>{response}</p> : null}
      <Footer />
    </div>
  );
}

export default App;
