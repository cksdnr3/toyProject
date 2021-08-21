import HandleClick from "../../hook/HandleClick";
import { useInput } from "../../hook/useInput";

const PasswordInput = () => {
  const maxLength = (value: string) => value.length < 10;

  const pw = useInput("", maxLength);
  console.log("pw: ", pw.value);

  return (
    <>
      <label>패스워드</label>
      <input type={"password"} {...pw} />
      <HandleClick value={pw.value} />
    </>
  );
};

export default PasswordInput;
