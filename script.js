document.addEventListener('DOMContentLoaded', () => {
  const codeInput = document.getElementById('codeInput');
  const languageSelect = document.getElementById('languageSelect');
  const convertBtn = document.getElementById('convertBtn');
  const debugBtn = document.getElementById('debugBtn');
  const qualityCheckBtn = document.getElementById('qualityCheckBtn');
  const responseDiv = document.getElementById('response');

  const BASE_URL = 'https://code-converter-api-yfl2.onrender.com'; // Replace with your backend URL

  const handleButtonClick = async (endpoint) => {
      const code = codeInput.value.trim();
      const language = languageSelect.value;
      if (!code) {
          alert('Please enter your code.');
          return;
      }

      try {
          const response = await fetch(`${BASE_URL}${endpoint}`, {
              method: 'POST',
              headers: {
                  'Content-Type': 'application/json',
              },
              body: JSON.stringify({ code, fromLanguage: language, toLanguage: language }),
          });

          const data = await response.json();
          responseDiv.innerHTML = data.convertedCode;
      } catch (error) {
          console.error('Error:', error);
          responseDiv.innerHTML = 'Error occurred while converting the code.';
      }
  };

  convertBtn.addEventListener('click', () => handleButtonClick('/convert'));
  debugBtn.addEventListener('click', () => handleButtonClick('/debug'));
  qualityCheckBtn.addEventListener('click', () => handleButtonClick('/check'));
});
