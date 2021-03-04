export interface Address {
  country: CountryCode | "All";
}

export type CountryCode = "US" | "AU";

export interface FieldDescriptor {
  field: string;
  format: string;
  optional?: true;
  enum?: true;
}

export type Format = Array<FieldDescriptor>;

export interface CountryMetadata {
  name: string;
  formats: Format[];
  enums: Record<string, string[]>;
}

export type Metadata = Record<CountryCode, CountryMetadata> & {
  enums: Record<string, string[]>;
};
