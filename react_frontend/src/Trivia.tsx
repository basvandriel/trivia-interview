import { useEffect, useState } from "react";
import Question from "./Question";

/**
 * Move into env
 */
 const API_URL = 'http://localhost:8080'

export const Trivia = () => {
    const [questions, setQuestions] = useState<Question[]>([]);

    
    useEffect(() => {
        const fetchQuestions = async () => {
          const response = await fetch(`${API_URL}/questions`);  
          const data = await response.json();
    
          setQuestions(data)
        }
        fetchQuestions();
      }, [])
    
      if(questions.length === 0) return <p>Loading.</p>

      const submit = async (answer: string) => {
        const question: string = questions[0].question
        console.log("Submitted answer "+ answer + "to question " + question)
    
        const response = await fetch(`${API_URL}/checkanswer`, {
          method: 'POST',
          body: JSON.stringify({
            question, answer
          }),
          headers: { 'Content-Type': 'application/json' },
        })
    
        // If the answer was correct0
        const ok: boolean = await response.json();
    
        console.log(ok)
      }
    
    return <>
        <p dangerouslySetInnerHTML={{ __html: questions[0].question}}/>
        <div>
            {questions[0].possible_answers.map((v, i) => (
            <label key={i} htmlFor={`answer_${i}`}>
                <input type={'radio'} name='possible_answer' id={`answer_${i}`} onChange={(e) => submit(v)} />
                {v.normalize()}
            </label>
            ))}
        </div>
    </>
}