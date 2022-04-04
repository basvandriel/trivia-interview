import { API_URL } from "../constants";

const useCheckAnswerRequest = () => {
  const check = async (question: string, answer: string) =>
    await fetch(`${API_URL}/checkanswer`, {
      method: "POST",
      body: JSON.stringify({
        question,
        answer,
      }),
      headers: { "Content-Type": "application/json" },
    });

  return check;
};

export default useCheckAnswerRequest;
