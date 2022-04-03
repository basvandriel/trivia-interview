import { useEffect, useState } from 'react';
import logo from './logo.svg';
import './App.css';

interface Question {
  category: string,
  difficulty: string,
  question: string,
  possible_answers: string[]
}

/**
 * Move into env
 */
const API_URL = 'http://localhost:8080'

function App() {
  const [questions, setQuestions] = useState<Question[]>([]);

  useEffect(() => {
    const fetchQuestions = async () => {
      const response = await fetch(`${API_URL}/questions`);  
      const data = await response.json();

      setQuestions(data)
    }
    fetchQuestions();
  }, [])

  if(questions.length === 0) return <p>"No questions yet!"</p>


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

  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p dangerouslySetInnerHTML={{ __html: questions[0].question}}/>

        <div>
          {questions[0].possible_answers.map((v, i) => (
            <div key={i} onClick={() => submit(v)}>
              <input type={'radio'} name='possible_answer'/>{v}
            </div>
          ))}
        </div>
      </header>
    </div>
  );
}

export default App;
