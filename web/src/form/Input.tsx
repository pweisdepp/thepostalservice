import { FC } from "react";

import FormGroup from "./FormGroup";

const Input: FC<{
  name: string;
  help?: string;
  type?: "text" | "number";
  value?: string;
  valueChange?: (val: string) => void;
  ariaLabel?: string;
}> = ({
  name,
  help = "",
  type = "text",
  value = "",
  valueChange = () => {},
  ariaLabel = `Input ${name}`,
}) => {
  return (
    <FormGroup name={name} help={help}>
      <input
        type={type}
        name={name}
        className="form-control"
        value={value}
        onChange={(e) => valueChange(e.target.value)}
        onBlur={(e) => valueChange(e.target.value)}
        aria-label={ariaLabel}
      />
    </FormGroup>
  );
};

export default Input;
