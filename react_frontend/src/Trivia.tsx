import { useEffect, useRef, useState } from "react";
import Question from "./Question";
import useQuestionsRequest from "./hooks/useQuestionsQuery";
import useCheckAnswerRequest from "./hooks/useCheckAnswerRequest";

export const Trivia = () => {
  const [questions, setQuestions] = useState<Question[]>([]);

  /**
   * To fetch the questions
   */
  const fetchQuestions = useQuestionsRequest();

  /**
   * To check the answer
   */
  const check = useCheckAnswerRequest();

  /**
   * Prevent StrictMode from rendering this component twice
   */
  const renderRef = useRef(false);

  /**
   * Load the questions on page load
   */
  useEffect(() => {
    if (renderRef.current === true) return;

    (async () => {
      const response = await fetchQuestions();
      const json = await response.json();

      setQuestions(json);
    })();
    renderRef.current = true;
  }, [fetchQuestions]);

  if (questions.length === 0) return <p>Loading.</p>;

  const submit = async (answer: string) => {
    const question: string = questions[0].question;
    const response = await check(question, answer);

    // If the answer was correct
    const ok: boolean = await response.json();

    console.log(ok);
  };

  return (
    <>
      <p dangerouslySetInnerHTML={{ __html: questions[0].question }} />
      <div>
        {questions[0].possible_answers.map((v, i) => (
          <label key={i} htmlFor={`answer_${i}`}>
            <input
              type="radio"
              id={`answer_${i}`}
              name="possible_answer"
              onChange={() => submit(v)}
            />
            <span dangerouslySetInnerHTML={{ __html: v }} />
          </label>
        ))}
      </div>
    </>
  );
};
