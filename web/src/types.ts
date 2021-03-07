export interface Address {
  country: CountryCode | "All";
  [k: string]: string;
}

export type CountryCode = "US" | "AU";

export interface FieldDescriptor {
  field: string;
  format: string;
  optional?: true;
  enumeration?: true;
}

export type Format = Array<FieldDescriptor>;

export interface CountryMetadata {
  name: string;
  formats: Format[];
  enumerations: Record<string, string[]>;
}

export type Metadata = Record<CountryCode, CountryMetadata> & {
  enumerations: Record<string, string[]>;
};
