function ModalBasic({ setModalOpen }) {
    return (
        <div className={styles.container}>
            <p>모달창입니다.</p>
            <button className={styles.close} onClick={() => {
                setModalOpen(false)}}>
                X
            </button>
        </div>
    );
}
export default ModalBasic;