import { FC, FormEvent, ReactElement } from "react";

const Form: FC<{ onSubmit?: () => void; autocomplete?: boolean }> = ({
  onSubmit = () => {},
  autocomplete = false,
  children,
}): ReactElement => {
  const doSubmit = (e: FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    onSubmit();
  };
  return (
    <form onSubmit={doSubmit} autoComplete={autocomplete ? "on" : "off"}>
      {children}
    </form>
  );
};

export default Form;
