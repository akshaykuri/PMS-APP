export const getFormattedCreatedOn = () => {
    const localDate = new Date();
    const istOffset = 5.5 * 60 * 60 * 1000; // IST is UTC+5:30
    const istDate = new Date(localDate.getTime() + istOffset);
    return istDate.toISOString().slice(0, 19); // Format as yyyy-MM-dd'T'HH:mm:ss
};