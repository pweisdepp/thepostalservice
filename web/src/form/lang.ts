export function titleCase(title: string): string {
  return title.replace(/(^|[ _])(\w)/g, (char) =>
    char.replace("_", " ").toLocaleUpperCase()
  );
}
