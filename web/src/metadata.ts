import { Metadata } from "./types";

export const FORMATS: Metadata = {
  US: {
    name: "United States",
    formats: [
      [
        { field: "street_number", format: "[0-9]+" },
        { field: "street_name", format: ".+" },
        {
          field: "street_type",
          format: "STREET_TYPE",
          optional: true,
          enumeration: true,
        },
        { field: "street_secondary", format: ".+", optional: true },
        { field: "city", format: ".+" },
        { field: "state", format: "US_STATE", enumeration: true },
        { field: "zip", format: "[0-9]{5}" },
      ],
    ],
    enumerations: {
      US_STATE: [
        "Alabama",
        "Alaska",
        "Arizona",
        "Arkansas",
        "California",
        "Colorado",
        "Connecticut",
        "Delaware",
        "District of Columbia",
        "Florida",
        "Georgia",
        "Hawaii",
        "Idaho",
        "Illinois",
        "Indiana",
        "Iowa",
        "Kansas",
        "Kentucky",
        "Louisiana",
        "Maine",
        "Maryland",
        "Massachusetts",
        "Michigan",
        "Minnesota",
        "Mississippi",
        "Missouri",
        "Montana",
        "Nebraska",
        "Nevada",
        "New Hampshire",
        "New Jersey",
        "New Mexico",
        "New York",
        "North Carolina",
        "North Dakota",
        "Ohio",
        "Oklahoma",
        "Oregon",
        "Pennsylvania",
        "Puerto Rico",
        "Rhode Island",
        "South Carolina",
        "South Dakota",
        "Tennessee",
        "Texas",
        "Utah",
        "Vermont",
        "Virginia",
        "Washington",
        "West Virginia",
        "Wisconsin",
        "Wyoming",
      ],
    },
  },
  AU: {
    name: "Australia",
    formats: [
      [
        { field: "street_number", format: "[0-9]+" },
        { field: "street_name", format: ".+" },
        {
          field: "street_type",
          format: "STREET_TYPE",
          optional: true,
          enumeration: true,
        },
        { field: "city", format: "AU_CITY", enumeration: true },
        { field: "province", format: "AU_PROVINCE", enumeration: true },
        { field: "zip", format: "[0-9]{4}" },
        { field: "country", format: "Australia", optional: true },
      ],
    ],
    enumerations: {
      AU_CITY: ["Sidney", "Perth", "Brisbane", "Adelaide", "..."],
      AU_PROVINCE: ["ACT", "NSW", "NT", "..."],
    },
  },
  enumerations: {
    STREET_TYPE: ["ALLEY", "ARC", "AVE", "..."],
  },
};

export function countries() {
  // Find `enumerations` key and remove it
  let countries = Object.keys(FORMATS);
  const idx = countries.indexOf("enumerations");
  countries.splice(idx, 1);
  return countries;
}
