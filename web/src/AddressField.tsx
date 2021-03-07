import { FC } from "react";

import Input from "./form/Input";
import Select from "./form/Select";
import { FORMATS } from "./metadata";
import { FieldDescriptor } from "./types";

function getEnum(enums: Record<string, string[]>, enumer: string): string[] {
  return enums[enumer] ?? FORMATS.enums[enumer] ?? [];
}

const AddressField: FC<{
  field: FieldDescriptor;
  enums: Record<string, string[]>;
  value: string;
  valueChange: (value: string) => void;
}> = ({ field, enums, value, valueChange }) => {
  if (field.enum && getEnum(enums, field.format) === undefined) {
    throw new Error(
      `Field {field.field} is enum with format ${
        field.format
      }, which is not present in (${Object.keys(enums).join(",")})`
    );
  }

  return (
    <>
      {field.enum ? (
        <Select
          name={field.field}
          value={value}
          values={getEnum(enums, field.format)}
          valueChange={valueChange}
        />
      ) : (
        <Input name={field.field} value={value} valueChange={valueChange} />
      )}
    </>
  );
};

export default AddressField;
