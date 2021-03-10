import { FC } from "react";
import { Address } from "./types";

const AddressResponses: FC<{ responses: Address[] }> = ({ responses }) => {
  return (
    <>
      <div className="alert alert-success">{responses.length} found</div>

      {responses.map((_) => (
        <AddressResponse address={_} />
      ))}
    </>
  );
};

const AddressResponse: FC<{ address: Address }> = ({ address }) => {
  return (
    <div className="container">
      {Object.entries(address).map(([k, v]) => (
        <dl className="row">
          <dt className="col-sm-3">{k}</dt>
          <dd className="col-sm-9">{v}</dd>
        </dl>
      ))}
    </div>
  );
};

export default AddressResponses;
