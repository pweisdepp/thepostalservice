import { FC } from "react";

const Input: FC<{
  name: string;
  help?: string;
  type?: "text" | "number";
  value?: string;
  valueChange?: (val: string) => void;
}> = ({
  name,
  help = "",
  type = "text",
  value = "",
  valueChange = () => {},
}) => {
  return (
    <div className="mb-3 form-group">
      <label htmlFor={name} className="form-label">
        {name}
      </label>
      <input
        type={type}
        name={name}
        className="form-control"
        value={value}
        onChange={(e) => valueChange(e.target.value)}
        onBlur={(e) => valueChange(e.target.value)}
        aria-describedBy={name + "Help"}
      />
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

export default Input;
