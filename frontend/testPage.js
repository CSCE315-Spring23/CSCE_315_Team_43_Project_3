
const testDB = async () => {
    const url = `http://localhost:8080/smook/test`;
    const request = {
        method: 'POST',
        headers: {
          'Content-Type': 'text/plain',
        },
        mode: 'cors',
        cache: 'default',
      };
    const response = await fetch(url, request);
    const parsed = await response.json();
}