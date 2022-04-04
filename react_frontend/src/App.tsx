import { useEffect, useState } from 'react';

function App() {
  const [questions, setQuestions] = useState([])

  useEffect(() => {
    (async () => {
      const response = await fetch("https://localhost:8080/questions")
      console.log('Fetching questions')
    })();
  }, [])

  return (
    <p>Yes</p>
  );
}

export default App;
