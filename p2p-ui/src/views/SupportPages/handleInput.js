import { toast } from "react-toastify";

export const handleDecimalInput = (value, setValue) => {
  // Allow only numbers with up to two decimal places
  const validValue = value.match(/^\d*\.?\d{0,2}$/);
  if (validValue) {
    setValue(value); // Update state with valid value
  }else{
    toast.error("Only Numbers with 2 decimals are allowed.")
  }
};

export const handleWholeNumberInput = (value, setValue) => {
  // Allow only numbers with up to two decimal places
  if (/^\d*$/.test(value)) {
    setValue(value);
  }else{
    toast.error("Only Numbers are allowed.")
  }
};

export const handleSpecialCharacters = (value, setValue) => {
  // Allow only digits or an empty string
  if (/^[a-zA-Z0-9 ]*$/.test(value)) {
    setValue(value);
  }else{
    toast.error("Special Characters are not allowed.")
  }
}

export const handleCalculationTotal = (cp = 0, qty = 0, setTotal) => {
  const cp1   = parseFloat(cp) || 0;
  const qty1  = parseFloat(qty) || 0;
  setTotal((cp1 * qty1).toFixed(2));
}