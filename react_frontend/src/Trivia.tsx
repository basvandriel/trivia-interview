import { useEffect, useRef, useState } from "react";
import Question from "./Question";
import useQuestionsRequest from "./hooks/useQuestionsQuery";
import useCheckAnswerRequest from "./hooks/useCheckAnswerRequest";

export const Trivia = () => {
  const [questions, setQuestions] = useState<Question[]>([]);

  /**
   * The current question
   */
  const [index, setIndex] = useState(0);

  /**
   * If the user completed the quiz
   */
  const [completed, setCompleted] = useState(false);

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

  /**
   * Proceeds to the next question
   */
  const next = () => setIndex((current) => current + 1);

  const submit = async (answer: string) => {
    const { question } = questions[index];
    const response = await check(question, answer);

    // If the answer was correct
    const ok: boolean = await response.json();

    console.log(ok);

    const end = index + 1 > questions.length - 1;

    if (!end) next();
    else setCompleted(true);
  };

  if (completed) {
    return <p>Nice job!</p>;
  }

  return (
    <>
      <p dangerouslySetInnerHTML={{ __html: questions[index].question }} />
      <div>
        {questions[index].possible_answers.map((v, i) => (
          <button key={i} id={`answer_${i}`} onClick={() => submit(v)}>
            <span dangerouslySetInnerHTML={{ __html: v }} />
          </button>
        ))}
      </div>
    </>
  );
};
