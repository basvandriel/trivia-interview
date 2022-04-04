import { API_URL } from "../constants";

const useQuestionsQuery = () => {
  return async () => {
    return await fetch(`${API_URL}/questions`);
  };
};

export default useQuestionsQuery;
