
const testDB = async () => {
    const url = `http://localhost:8080/smook/test`;
    const response = await fetch(url);
    const parsed = await response.json();
}