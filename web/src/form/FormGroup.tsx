import { FC } from "react";

import { titleCase } from "./lang";

const FormGroup: FC<{ name: string; ariaLabel?: string; help?: string }> = ({
  name,
  help = "",
  children,
}) => {
  return (
    <div className="mb-3 form-group">
      <label htmlFor={name} className="form-label">
        {titleCase(name)}
      </label>
      {children}
      {help == "" ? (
        []
      ) : (
        <small id={name + "Help"} className="form-text text-muted">
          {help}
        </small>
      )}
    </div>
  );
};

export default FormGroup;
