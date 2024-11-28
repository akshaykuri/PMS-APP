import { useRef } from 'react'

export const draggableModal = () => {

  const modalRef    = useRef(null);
  const offSet      = useRef({ x: 0, y: 0 });
  const isDragging  = useRef(false);

  const handleMouseDown = (e) => {
    isDragging.current = true;
    offSet.current = {
      x: e.clientX - modalRef.current.getBoundingClientRect().left,
      y: e.clientY - modalRef.current.getBoundingClientRect().top,
    };
  };

  const handleMouseMove = (e) => {
    if (isDragging.current) {
      modalRef.current.style.left = `${e.clientX - offSet.current.x}px`;
      modalRef.current.style.top = `${e.clientY - offSet.current.y}px`;
      modalRef.current.style.position = 'absolute';
    }
  };

  const handleMouseUp = () => {
    isDragging.current = false;
  };

  return {
    modalRef,
    handleMouseDown,
    handleMouseMove,
    handleMouseUp,
  };
};