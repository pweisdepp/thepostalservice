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
}> = ({ field, enums }) => {
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
          values={getEnum(enums, field.format)}
        ></Select>
      ) : (
        <Input name={field.field}></Input>
      )}
    </>
  );
};

export default AddressField;
