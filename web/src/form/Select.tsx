import { FC } from "react";

import FormGroup from "./FormGroup";

const Select: FC<{
  name: string;
  value?: string;
  values: string[];
  valueChange?: (value: string) => void;
  ariaLabel?: string;
  all?: true;
  none?: true;
}> = ({
  name,
  value = "",
  values,
  valueChange = () => {},
  ariaLabel = `Select ${name}`,
  all,
  none,
}) => {
  return (
    <FormGroup name={name}>
      <select
        name="name"
        value={value}
        className="form-select"
        aria-label={ariaLabel}
        onChange={(e) => valueChange(e.target.value)}
      >
        {none ? <option key=""></option> : []}
        {all ? <option key="All">All</option> : []}
        {values.map((val) => (
          <option key={val}>{val}</option>
        ))}
      </select>
    </FormGroup>
  );
};

export default Select;
